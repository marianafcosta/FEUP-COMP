/* Generated By:JJTree: Do not edit this line. ASTCondition.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTCondition extends SimpleNode {
  public ASTCondition(int id) {
    super(id);
  }

  public ASTCondition(Jmm p, int id) {
    super(p, id);
  }

  @Override
  public void checkNodeSemantic() {
    ((SimpleNode) this.jjtGetChild(0)).scope = this.scope;
    if(!((SimpleNode) this.jjtGetChild(0)).getReturnType().equals("boolean")) {
      super.printSemanticError("An if condition must return a boolean value");
    }
  }

}
/* JavaCC - OriginalChecksum=69d0e3cf45787dde505033fffedd41a4 (do not edit this line) */
