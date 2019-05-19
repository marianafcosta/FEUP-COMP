/* Generated By:JJTree: Do not edit this line. ASTThis.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTThis extends SimpleNode {
  
  public ASTThis(int id) {
    super(id);
    this.actualReturnType = "this";
  }

  public ASTThis(Jmm p, int id) {
    super(p, id);
    this.actualReturnType = "this";
  }

  @Override
  public String getActualReturnType() {
    return this.actualReturnType;
  }
  

  @Override
  public String getReturnType() {
    //check if node has children
    if (this.actualReturnType.equals("this")) {
      this.actualReturnType = SimpleNode.className;
    }
    if (this.jjtGetNumChildren() > 0) {
      SimpleNode child = (SimpleNode) this.jjtGetChild(0);
      child.scope = this.scope;
      return child.getReturnType();
    } else {
      return this.actualReturnType;
    }
  }

  @Override
  public void checkNodeSemantic() {
    this.actualReturnType = SimpleNode.className;
  }

}
/* JavaCC - OriginalChecksum=322a4f946530563b6953bb802410381e (do not edit this line) */
