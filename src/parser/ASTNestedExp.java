/* Generated By:JJTree: Do not edit this line. ASTNestedExp.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTNestedExp extends SimpleNode {
  public ASTNestedExp(int id) {
    super(id);
    this.actualReturnType = "";
  }

  public ASTNestedExp(Jmm p, int id) {
    super(p, id);
    this.actualReturnType = "";
  }

  @Override
  public String getActualReturnType() {
    if (this.actualReturnType.equals("")) {
      ((SimpleNode) (this.jjtGetChild(1))).scope = this.scope;
      this.actualReturnType = ((SimpleNode) (this.jjtGetChild(1))).getReturnType();
    }
    return this.actualReturnType;
  }

  @Override
  public String getReturnType() {
    if (this.actualReturnType.equals("")) {
      ((SimpleNode) (this.jjtGetChild(1))).scope = this.scope;
      this.actualReturnType = ((SimpleNode) (this.jjtGetChild(1))).getReturnType();
    }
    return this.actualReturnType;
  }

}
/* JavaCC - OriginalChecksum=9be9be827947ff67346b9799bf0ef8af (do not edit this line) */
