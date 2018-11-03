package JavaStudy;

class Car
{
    int num;
    String color;

    void run() {
        System.out.print(num +" "+ color);
    }
}

class CarDemo
    {
        public static void main(String[] args)
        {
            Car a=new Car();
            a.color="red";
            a.num=1;

        }
    }

