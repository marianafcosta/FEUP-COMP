/* Generated By:JJTree: Do not edit this line. ASTNew.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTNew extends SimpleNode {

  public String identifier;

  public ASTNew(int id) {
    super(id);
  }

  public ASTNew(Jmm p, int id) {
    super(p, id);
  }

  @Override
  public String getReturnType() {
    //check if node has children
    if (this.jjtGetNumChildren() > 0 
        && (((SimpleNode) this.jjtGetChild(0)).getId() == JmmTreeConstants.JJTCALL)) {
      SimpleNode child = (SimpleNode) this.jjtGetChild(0);
      child.scope = this.scope;
      return child.getReturnType();
    } else {
      return this.actualReturnType;
    }
  }

  @Override
  public String getActualReturnType() {
    return this.actualReturnType;
  } 

  @Override
  public void checkNodeSemantic() {
    
  }

  public void dump(String prefix) {
    System.out.println(toString(prefix) + ": " + this.actualReturnType);
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
/* JavaCC - OriginalChecksum=4046dc8323616fdadc6cfb7161154bc7 (do not edit this line) */
