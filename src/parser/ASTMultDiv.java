/* Generated By:JJTree: Do not edit this line. ASTMultDiv.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTMultDiv extends SimpleNode {

  protected String op;

  public ASTMultDiv(int id) {
    super(id);
    this.returnType = "int";
  }

  public ASTMultDiv(Jmm p, int id) {
    super(p, id);
    this.returnType = "int";
  }

  public String getOp() {
    return this.op;
  }

  public void setOp(String op){
    this.op = op;
  }

  @Override  
  public void checkNodeSemantic() {
    SimpleNode lhs, rhs;
    lhs = (SimpleNode) this.jjtGetChild(0); rhs = (SimpleNode) this.jjtGetChild(1);
    if (lhs.returnType != "int" || rhs.returnType != "int") {
      super.printSemanticError("Both sides of a " + this.op + " operation should be of type int");
    }
  }

  public String toString() {
    return "";
  }

  public void dump(String prefix) {
    System.out.println(toString(prefix) + "(" + this.op + ")");
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
/* JavaCC - OriginalChecksum=f539d2bff29a7732c5efaf895f056976 (do not edit this line) */
