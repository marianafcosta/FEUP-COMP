/* Generated By:JJTree: Do not edit this line. ASTAssign.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

import semantic.*;

public
class ASTAssign extends SimpleNode {

  public String lhsIdentifier;

  public ASTAssign(int id) {
    super(id);
  }

  public ASTAssign(Jmm p, int id) {
    super(p, id);
  }

  public String getLhs() {
    return this.lhsIdentifier;
  }

  @Override
  public String getActualReturnType() {
    if (this.actualReturnType.equals("")) {
      STO lhs = SimpleNode.symbolTable.doesSymbolExist(this.lhsIdentifier, this.scope);
      if (lhs == null) {
        lhs = SimpleNode.symbolTable.doesGlobalExist(this.lhsIdentifier);
      }
      this.actualReturnType = lhs.getType();
    }
    return this.actualReturnType;
  }

  @Override
  public void checkNodeSemantic() {

    STO lhs = SimpleNode.symbolTable.doesSymbolExist(this.lhsIdentifier, this.scope);
    if (lhs == null) {
      lhs = SimpleNode.symbolTable.doesGlobalExist(this.lhsIdentifier);
    }
    
    if (lhs == null) {
      super.printSemanticError("Variable " + this.lhsIdentifier + " was not declared");
      return;
    }
    
    SimpleNode rhs = (SimpleNode) this.jjtGetChild(0);
    rhs.scope = this.scope;
    String rhsReturn = rhs.getReturnType();
    String lhsReturn = lhs.getType();
    if (!rhsReturn.equals(lhsReturn) && !rhsReturn.equals("void") && !(rhsReturn.equals(SimpleNode.className) && (lhsReturn.equals(SimpleNode.className) || lhsReturn.equals(SimpleNode.extend)))) {
      super.printSemanticError("Variable types not compatible");
      return;
    }

    lhs.initialize();

  }

  public void dump(String prefix) {
    System.out.println(toString(prefix) + ": " + this.lhsIdentifier);
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
/* JavaCC - OriginalChecksum=42171107bb3b507a7e69d9c464358f53 (do not edit this line) */
