package sharp_parent_test.common;

public class ConstructionMethodTest extends ConstructionMethod{
    public void test(){
        int a =bind();
        System.out.println(a);


    }
    public int bind(){
        int isActivated = 0;
        new ConstructionMethodTest().push();
        return isActivated;
    }
    public ConstructionMethodTest(){
        super();
    }


}
