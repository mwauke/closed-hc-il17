/*
Use listutils to read a pair of list values from two files,
compare them, and print the resulting diff in CriticMarkup
describing mods needed to change list1 into list2.
*/
String usage = "Usage: groovy veritcalcm.groovy File1 File2"

@GrabResolver(name='beta', root='http://beta.hpcc.uh.edu/nexus/content/repositories/releases')
@Grab(group='edu.holycross.shot', module='listutils', version='1.2.0')

@Grab(group='com.ibm.icu', module='icu4j', version='3.4.4')


import com.ibm.icu.text.UCharacterIterator
import edu.holycross.shot.listutils.*


// cf lists expressed as 1 item per line

if (args.size() != 2) {
  System.err.println usage
  System.exit(-1)
}

File f1 = new File(args[0])
File f2 = new File(args[1])

ArrayList list1 = f1.getText().readLines()
ArrayList list2 = f2.getText().readLines()
ListDiff ldiff = new ListDiff(list1,list2)

println "#Results of comparison"
println "Number of items in **list1**: " + list1.size() + "\n"
println "Number of items in **list2**: " + list2.size() + "\n"
println "Number of common items: " + ldiff.lcs.size() + "\n"

//println ldiff.toCriticMarkup()

ldiff.diffs.each { diff  ->

  diff.each { d ->


    switch (d.dtype) {
      case DescriptorType.ONE :
      print "{--${d.token}--} "
      break
      case DescriptorType.TWO :
      print "{++${d.token}++} "
      break
      default:
      // skip
      break

    }


  }
}
