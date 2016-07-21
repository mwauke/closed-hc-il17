
/*
Tokenize a tabular file representing a text following HMT project conventions.

Reads a tabular file, and writes out two columns:

1. CTS URN with substring identifying token
2. CITE URN classifying the token

*/


@GrabResolver(name='beta', root='http://beta.hpcc.uh.edu/nexus/content/repositories/releases')
@Grab(group='org.homermultitext', module='hmt-utils', version='0.7.21')
import org.homermultitext.utils.HmtEditorialTokenization

String usage =  "groovy tokenizeTab.groovy TABFILE"
if (args.size() != 1) {
  println usage
  System.exit(-1)
}

File tabFile = new File(args[0])
HmtEditorialTokenization toker = new HmtEditorialTokenization()
def allTokens = toker.tokenizeTabFile(tabFile,"#")

// Whatever you like for separating columns:
String separator = "\t"
allTokens.each { t ->
  println t[0] + separator + t[1]
}
