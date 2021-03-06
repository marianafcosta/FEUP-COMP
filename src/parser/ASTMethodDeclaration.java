/* Generated By:JJTree: Do not edit this line. ASTMethodDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

public
class ASTMethodDeclaration extends SimpleNode {

  protected String name;
  public String type;
  String stIdentifier;

  public ASTMethodDeclaration(int id) {
    super(id);
  }

  public ASTMethodDeclaration(Jmm p, int id) {
    super(p, id);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public String getActualReturnType() {
    return "void";
  }

  @Override
  public void checkNodeSemantic() {
    this.scope = this.name; 
    if (this.jjtGetNumChildren() > 0 && ((SimpleNode) this.jjtGetChild(0)).getId() == JmmTreeConstants.JJTMETHODARGUMENTS) {
      ASTMethodArguments args = ((ASTMethodArguments) this.jjtGetChild(0));
      for (int i = 0; i < args.jjtGetNumChildren(); i++) {
        if (((ASTMethodArgument) args.jjtGetChild(i)).getId() != JmmTreeConstants.JJTMETHODARGUMENT) {
          break;
        } else {
          this.scope += ((ASTMethodArgument) args.jjtGetChild(i)).type;
        }
      }
    }

  }

  public void dump(String prefix) {
    System.out.println(toString(prefix) + ": type: " + this.type);
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
/* JavaCC - OriginalChecksum=007fb8591b691f26d211e537ce9748f6 (do not edit this line) */
