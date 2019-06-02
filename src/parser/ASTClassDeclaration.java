/* Generated By:JJTree: Do not edit this line. ASTClassDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTClassDeclaration extends SimpleNode {

  protected String name;
  protected String extendsIdent = "";

  public ASTClassDeclaration(int id) {
    super(id);
  }

  public ASTClassDeclaration(Jmm p, int id) {
    super(p, id);
    
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
    SimpleNode.className = this.name;
  }

  public String getExtends() {
    return this.extendsIdent;
  }

  public void setExtends(String extendsIdent) {
    SimpleNode.extend = extendsIdent;
    this.extendsIdent = extendsIdent;
  }

  public void dump(String prefix) {
    System.out.println(toString(prefix) + ": " + this.name + (!(this.extendsIdent.equals("")) ? " (extends " + this.extendsIdent + ")" : ""));
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
/* JavaCC - OriginalChecksum=b6fffd4a85b812aba211bb13665f2cc5 (do not edit this line) */
