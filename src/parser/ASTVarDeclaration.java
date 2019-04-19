/* Generated By:JJTree: Do not edit this line. ASTVarDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTVarDeclaration extends SimpleNode {

  public String type;
  protected String identifier;
  
  public ASTVarDeclaration(int id) {
    super(id);
  }

  public ASTVarDeclaration(Jmm p, int id) {
    super(p, id);
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void dump(String prefix) {
    System.out.println(toString(prefix) + ": " + this.identifier + " type: " + this.type);
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        if (n != null) {
          n.dump(prefix + "    ");
        }
      }
    }
  }

}
/* JavaCC - OriginalChecksum=3f12004920038871459799f1561e5869 (do not edit this line) */