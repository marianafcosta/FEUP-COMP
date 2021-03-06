/* Generated By:JJTree: Do not edit this line. ASTWhile.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTWhile extends SimpleNode {

  static int count = 0;
  int labelId;

  public ASTWhile(int id) {
    super(id);
    this.labelId = count;
    count++;
  }

  public ASTWhile(Jmm p, int id) {
    super(p, id);
    this.labelId = count;
    count++;
  }

  public int getLabelId() {
    return this.labelId;
  }

  @Override
  public void checkNodeSemantic() {
    ((SimpleNode) this.jjtGetChild(0)).scope = this.scope;
    if (!((SimpleNode) this.jjtGetChild(0)).getReturnType().equals("boolean")) {
      super.printSemanticError("The expression inside the while condition must return a boolean value");
    }
  }

}
/* JavaCC - OriginalChecksum=7a146956b7611288dd0b57a32590b923 (do not edit this line) */
