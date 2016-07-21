/*
Read a list of line refernces for e-dented line, and a list of first 3 words of each line.
Print first 3 if also in list of e-dented.

Usage: groovy first3edentedwords.groovy EDENTLISTFILE FIRSTTHREEFILE

 */

File edentFile = new File(args[0])
def edentList = edentFile.readLines()

File wordsFile = new File(args[1])

wordsFile.each { l ->
  def cols = l.split(/\t/)
  if (edentList.contains(cols[0])) {
    println l
  }
}