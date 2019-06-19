package sharp_parent_test.common;

public class ConstructionMethodService extends ConstructionMethod {
    public void test() {
        int a = bind();
        System.out.println(a);


    }

    public int bind() {
        int isActivated = 0;
        new ConstructionMethodService().push();
        return isActivated;
    }

    public ConstructionMethodService() {
        super();
    }


}
