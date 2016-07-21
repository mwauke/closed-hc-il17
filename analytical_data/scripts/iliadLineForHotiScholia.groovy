/*
read list of hoti scholia, read map of scholia->Iliad, and
output list of iliad lines with hoti scholia
*/

if (args.size() != 2) {
  System.err.println "Usage: groovy iliadLineForHotiScholia.groovy HOTISCHOLIA SCHOLIAHASHFILE"
  System.exit(-1)
}


File hoti = new File(args[0])
File scholIliadMapFile = new File(args[1])

def scholIliadMap = [:]

scholIliadMapFile.eachLine {
  def cols = it.split(/\t/)
  String scholion = cols[0]
  String iliad = cols[1]
  scholIliadMap[scholion] = iliad
}

hoti.eachLine { s -> 
  if (scholIliadMap[s]) {
    println scholIliadMap[s]
  }
}