package pkg;

//- @Generics defines/binding GAbs
//- Class childof GAbs
//- GAbs.node/kind abs
//- GAbs param.0 TVar
//- @T defines/binding TVar
//- TVar.node/kind absvar
public class Generics<T> {
  //- @obj defines/binding V
  //- V typed Obj
  Object obj;

  //- @print defines/binding PrintMethod
  //- @print defines/binding PrintAbs
  //- PrintMethod childof PrintAbs
  //- PrintAbs.node/kind abs
  //- @P defines/binding PVar
  //- PrintAbs param.0 PVar
  //- PVar.node/kind absvar
  //- PVar bounded/upper Obj
  public static <P> void print(
      //- @P ref PVar
      P p) {
    System.out.println(p.toString());
  }

  //- @T ref TVar
  public void g(T t) {}

  public static void f() {
    //- @"Generics<String>" ref GType
    //- @gs defines/binding GVar
    //- GVar typed GType
    //- GType.node/kind tapp
    //- GType param.0 Class
    //- GType param.1 Str
    Generics<String> gs =
        //- @"Generics<String>" ref GType
        new Generics<String>();

    //- @"Optional<Generics<String>>" ref OType
    //- OType.node/kind tapp
    //- OType param.0 Optional
    //- OType param.1 GType
    //- @opt defines/binding OVar
    //- OVar typed OType
    Optional<Generics<String>> opt;
  }


  //- @Optional defines/binding OptionalAbs
  //- OptionalClass childof OptionalAbs
  //- @T defines/binding OptionalTVar
  //- OptionalTVar.node/kind absvar
  private static class Optional<T> {}

  //- @BV defines/binding BVar
  //- BVar.node/kind absvar
  //- @List ref List
  //- @Inter ref Inter
  //- BVar bounded/upper List
  //- BVar bounded/upper Inter
  private static class Bounded<BV extends java.util.List & Inter> {}

  //- @classTypeVarBound defines/binding ClassTypeVarBoundFunc
  //- @E defines/binding EVar
  //- EVar bounded/upper TVar
  //- ClassTypeVarBoundFunc named vname("pkg.Generics.<E extends pkg.Generics~T>classTypeVarBound()","","","","java")
  public <E extends T> void classTypeVarBound() {}

  //- @X defines/binding XVar
  public <X,
      //- @ownTypeVarBound defines/binding OwnTypeVarBoundFunc
      //- @Y defines/binding YVar
      //- YVar bounded/upper XVar
      //- OwnTypeVarBoundFunc named vname("pkg.Generics.<X,Y extends X>ownTypeVarBound()","","","","java")
      Y extends X> void ownTypeVarBound() {}


  // Verify that, if there are interface bounds, then java.lang.Object appears as a superclass bound
  // (and in the type parameter's signature) iff it's provided explicitly.
  // This matters because a bound of Object&Iface is actually different from a bound of just Iface:
  // The former's erasure is Object, the latter's is Iface, and erasure is a language-level concept
  // (see e.g., https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.6)

  // We test the cases of single vs. multiple interface bounds because those have different code paths
  // (the latter is implemented by javac as an intersection type).

  // With only an (implicit or explicit) bound of Object, do emit a bounded/upper edge,
  // but don't add the superfluous "extends java.lang.Object" in the name.

  //- @noIFaceBound defines/binding Func
  //- @S0 defines/binding S0Var
  //- S0Var bounded/upper Obj
  //- Func named vname("pkg.Generics.<S0>noIFaceBound()","","","","java")
  public <S0> void noIFaceBound() {}

  //- @objAndNoIFaceBound defines/binding OFunc
  //- @S1 defines/binding S1Var
  //- S1Var bounded/upper Obj
  //- OFunc named vname("pkg.Generics.<S1>objAndNoIFaceBound()","","","","java")
  public <S1> void objAndNoIFaceBound() {}

  // If there is at least one interface bound, only emit a bound of java.lang.Object if it was explicit.

  //- @oneIFaceBound defines/binding IFunc
  //- @S2 defines/binding S2Var
  //- @List ref List
  //- !{ S2Var bounded/upper Obj }
  //- S2Var bounded/upper List
  //- IFunc named vname("pkg.Generics.<S2 extends java.util.List>oneIFaceBound()","","","","java")
  public <S2 extends java.util.List> void oneIFaceBound() {}

  //- @objAndOneIFaceBound defines/binding OIFunc
  //- @S3 defines/binding S3Var
  //- @List ref List
  //- S3Var bounded/upper Obj
  //- S3Var bounded/upper List
  //- OIFunc named vname("pkg.Generics.<S3 extends java.lang.Object&java.util.List>objAndOneIFaceBound()","","","","java")
  public <S3 extends Object & java.util.List> void objAndOneIFaceBound() {}

  //- @twoIfaceBounds defines/binding IIFunc
  //- @S4 defines/binding S4Var
  //- @List ref List
  //- @Inter ref Inter
  //- !{ S4Var bounded/upper Obj }
  //- S4Var bounded/upper List
  //- S4Var bounded/upper Inter
  //- IIFunc named vname("pkg.Generics.<S4 extends java.util.List&pkg.Generics.Inter>twoIfaceBounds()","","","","java")
  public <S4 extends java.util.List & Inter> void twoIfaceBounds() {}

  //- @objAndTwoIFaceBounds defines/binding OIIFunc
  //- @S5 defines/binding S5Var
  //- @List ref List
  //- @Inter ref Inter
  //- S5Var bounded/upper Obj
  //- S5Var bounded/upper List
  //- S5Var bounded/upper Inter
  //- OIIFunc named vname("pkg.Generics.<S5 extends java.lang.Object&java.util.List&pkg.Generics.Inter>objAndTwoIFaceBounds()","","","","java")
  public <S5 extends Object & java.util.List & Inter> void objAndTwoIFaceBounds() {}

  //- @Inter defines/binding Inter
  private static interface Inter {}
}
