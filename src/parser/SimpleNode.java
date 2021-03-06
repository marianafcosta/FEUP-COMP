/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package parser;

import semantic.*;

public class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children; 
  protected int id;
  protected Object value;
  protected Jmm parser;
  protected String scope;
  protected static ST symbolTable;
  protected static String className = "";
  protected static String extend = "";
  protected String returnType = "";
  protected String actualReturnType = "";
  protected int beginLine;
  protected static boolean errorDetected = false;
  protected boolean optimizationO = false;
  public void setBeginLine( int line ) { beginLine = line ;}
  public int getBeginLine() { return beginLine ; }

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(Jmm p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) { this.value = value; }
  public Object jjtGetValue() { return value; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() {
    return JmmTreeConstants.jjtNodeName[id];
  }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        if (n != null) {
          n.dump(prefix + "    ");
        }
      }
    }
  }

  public int getId() {
    return id;
  }

  public static ST getSymbolTable() {
    return symbolTable;
  }

  public String getScope() {
    return this.scope;
  }

  public static String getClassName() {
    return className;
  }

  public void setOptimizationO(boolean optimizationO) {
    this.optimizationO = optimizationO;
  }

  public boolean getOptimizationO() {
    return this.optimizationO;
  }

  public void checkNodeSemantic() {}; 

  public String getReturnType() {
    return "";
  };
  
  public String getActualReturnType() {
    return "";
  }

  public final static void flagError() {
    SimpleNode.errorDetected = true;
  }

  public static boolean errorDetected() {
    return SimpleNode.errorDetected;
  }
  
  public void checkSemantics() {

    if (this.parent == null) {
      this.scope = "global";
    } else {
      this.scope = ((SimpleNode) this.parent).scope;
    }

    this.checkNodeSemantic();
    
    if(this.children != null) {
      for(Node node : this.children) {
        ((SimpleNode) node).checkSemantics();
      }
    }

  }
  
  public void buildSymbolTable() {

    if (parent == null && this.symbolTable == null) { //root node 
      SimpleNode.symbolTable = new ST();
      STFunction globalTable = new STFunction();
      globalTable.isGlobal();
      SimpleNode.symbolTable.addFunction("global", globalTable);

      String nodeType;
      Node[] children = ((SimpleNode) this.children[0]).children;
  
      for(Node node : children) {
        nodeType = ((SimpleNode) node).toString();
        if (nodeType.equals("MethodDeclaration") || nodeType.equals("MainDeclaration")) {
          analyzeMethodDeclaration(node);
        } else if (nodeType.equals("VarDeclaration")) {
          if (!this.symbolTable.addGlobal(((ASTVarDeclaration) node).getIdentifier(), new STO(((ASTVarDeclaration) node).type))) {
            this.printSemanticError("Variable " + ((ASTVarDeclaration) node).getIdentifier() + " already declared");
          } 
        }
      }
    }

  }

  public void analyzeMethodDeclaration(Node node) {

    boolean paramsChecked = false;

    STFunction stFunction = new STFunction();
    String stFunctionName = null;
    String nodeType = ((SimpleNode) node).toString();
    if (nodeType.equals("MethodDeclaration")) {
      stFunctionName = ((ASTMethodDeclaration) node).getName();
      stFunction.setReturn(new STO(((ASTMethodDeclaration) node).type));
    } else {
      stFunctionName = "main" + "String[]";
      stFunction.setReturn(new STO("void"));
      stFunction.isGlobal(); // set index to 0 (main is static) 
      stFunction.addSymbol("args", new STO("String"), true);
    }

    Node[] children = ((SimpleNode) node).children;
    String args = "";
    
    for(Node childNode : children) {
      nodeType = ((SimpleNode) childNode).toString();
      if (!paramsChecked) {
        if (nodeType.equals("MethodArguments")) {
          for(Node methodArg: ((SimpleNode) childNode).children) {
            ((ASTMethodArgument) methodArg).returnType = ((ASTMethodArgument) methodArg).type;
            args += ((ASTMethodArgument) methodArg).type;
            if (!stFunction.addSymbol(((ASTMethodArgument) methodArg).getIdentifier(), new STO(((ASTMethodArgument) methodArg).type), true)) {
              this.printSemanticError("Argument " + ((ASTMethodArgument) methodArg).getIdentifier() + " already declared");
            };
          }
        } else {
          if(!nodeType.equals("VarDeclaration")) {
            break;
          } else {
            ((ASTVarDeclaration) childNode).returnType = ((ASTVarDeclaration) childNode).type;
            if (!stFunction.addSymbol(((ASTVarDeclaration) childNode).getIdentifier(), new STO(((ASTVarDeclaration) childNode).type), false)) {
              this.printSemanticError("Variable " + ((ASTVarDeclaration) childNode).getIdentifier() + " already declared");
            };
          }
        }
        paramsChecked = true;
        continue;
      }

      if(!nodeType.equals("VarDeclaration")) {
        break;
      } else {
        ((ASTVarDeclaration) childNode).returnType = ((ASTVarDeclaration) childNode).type;
        if (!stFunction.addSymbol(((ASTVarDeclaration) childNode).getIdentifier(), new STO(((ASTVarDeclaration) childNode).type), false)) {
          this.printSemanticError("Variable " + ((ASTVarDeclaration) childNode).getIdentifier() + " already declared");
        };
      }

    }

    if (((SimpleNode) node).getId() == JmmTreeConstants.JJTMETHODDECLARATION) {
      stFunctionName += args;
    }

    if (!SimpleNode.symbolTable.addFunction(stFunctionName, stFunction)) {
      this.printSemanticError("Function " + stFunctionName + " already declared");
    }
  
  }

  public void printSemanticError(String errorMsg) {
    this.flagError();
    System.out.println("Semantic Error at line " + beginLine + ": " + errorMsg + ".");
  }

}

/* JavaCC - OriginalChecksum=197c934e78284e57260ea8dc7762052b (do not edit this line) */
