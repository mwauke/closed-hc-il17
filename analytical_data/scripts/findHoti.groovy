/*
Reads a tokenization file, and grabs first 4 tokens of a citable unit, strip accents and breathings,
writes out URN + 4 tokens in UTF-8 KFC.

Usage: groovy firstNtokens.groovy FILE NTOKENS

*/


@GrabResolver(name='beta', root='http://beta.hpcc.uh.edu/nexus/content/repositories/releases')
@Grab(group='edu.holycross.shot', module='greeklang', version='2.3.1')
@Grab(group='edu.harvard.chs', module='cite', version='2.0.2')



import edu.holycross.shot.orthography.*
import edu.harvard.chs.cite.CtsUrn

File f = new File(args[0])
Integer limit = args[1].toInteger()

String prevRef = ""
Integer currCount = 0
f.eachLine { l ->
  cols = l.split(/\t/)
  if (cols[1] != "urn:cite:hmt:tokentypes.punctuation") {
    try {
      CtsUrn longUrn = new CtsUrn(cols[0])
      String currRef = longUrn.getUrnWithoutPassage() + longUrn.getRef()
      GreekString gs =  new GreekString(longUrn.getSubref(), true)
      
      if (currRef == prevRef) {
	currCount++;
	if (currCount < limit) {
	  //print "#" + longUrn.getSubref()
	  //check for hoti
	  if (GreekString.stripAccents(gs.toString()).replaceAll(/[\)\(]/,'') == "oti") {
	    println currRef
	  }
	}
	
      } else {
	currCount = 0
	if (GreekString.stripAccents(gs.toString()).replaceAll(/[\)\(]/,'') == "oti") {
	  println currRef
	}
      }
      prevRef = currRef
      
    } catch (Exception e ) {
      System.err.println "Failed on ${cols[0]}: ${e}"
    }
  }
}

/*
GreekString gs = new GreekString("ὅτι", true)
println gs.toString(true) + " -> " + GreekString.stripAccents(gs.toString())
// manually remove breathings..
*/