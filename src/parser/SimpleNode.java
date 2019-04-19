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
  protected ST symbolTable;

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

  public ST getSymbolTable() {
    return this.symbolTable;
  }

  public void checkNodeSemantic() {}; 
  
  public void checkSemantics() {

    if (this.parent == null) {
      this.scope = "global";
    } else {
      this.scope = ((SimpleNode) this.parent).scope;
      this.symbolTable = ((SimpleNode) this.parent).symbolTable;
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
      this.symbolTable = new ST();
      this.symbolTable.addFunction("global", new STFunction());
      STFunction globalTable = this.symbolTable.getFunctionTable().get("global");
    
      String nodeType;
      Node[] children = ((SimpleNode) this.children[0]).children;
  
      for(Node node : children) {
        nodeType = ((SimpleNode) node).getClass().getSimpleName();
        if (nodeType.equals("ASTMethodDeclaration") || nodeType.equals("ASTMainDeclaration")) {
          analyzeMethodDeclaration(node);
        } else if (nodeType.equals("ASTVarDeclaration")) {
          //kinda esparguete, besides deve haver uma entrada especial para a tabela global
          globalTable.addSymbol(((ASTVarDeclaration) node).getIdentifier(), new STO(((ASTVarDeclaration) node).type), true); 
        }
      }
    }

  }

  public void analyzeMethodDeclaration(Node node) {

    boolean paramsChecked = false;

    STFunction stFunction = new STFunction();
    String stFunctionName = null;
    if (((SimpleNode) node).getClass().getSimpleName().equals("ASTMethodDeclaration")) {
      stFunctionName = ((ASTMethodDeclaration) node).getName();
      stFunction.setReturn(new STO(((ASTMethodDeclaration) node).type));
    } else {
      stFunctionName = "main";
      //TODO add void return type
    }

    Node[] children = ((SimpleNode) node).children;
    
    for(Node childNode : children) {
      if (!paramsChecked) {
        if (((SimpleNode) childNode).getClass().getSimpleName().equals("ASTMethodArguments")) {
          for(Node methodArg: ((SimpleNode) childNode).children) {
            stFunction.addSymbol(((ASTMethodArgument) methodArg).getIdentifier(), new STO(((ASTMethodArgument) methodArg).type), true);
          }
        } else {
          if(!((SimpleNode) childNode).getClass().getSimpleName().equals("ASTVarDeclaration")) {
            break;
          } else {
            stFunction.addSymbol(((ASTVarDeclaration) childNode).getIdentifier(), new STO(((ASTVarDeclaration) childNode).type), false);
          }
        }
        paramsChecked = true;
        continue;
      }

      if(!((SimpleNode) childNode).getClass().getSimpleName().equals("ASTVarDeclaration")) {
        break;
      } else {
        stFunction.addSymbol(((ASTVarDeclaration) childNode).getIdentifier(), new STO(((ASTVarDeclaration) childNode).type), false);
      }

    }

    this.symbolTable.addFunction(stFunctionName, stFunction);
  
  }

}

/* JavaCC - OriginalChecksum=197c934e78284e57260ea8dc7762052b (do not edit this line) */
