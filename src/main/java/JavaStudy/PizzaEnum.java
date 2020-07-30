package JavaStudy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 枚举
 * https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/basic/%E7%94%A8%E5%A5%BDJava%E4%B8%AD%E7%9A%84%E6%9E%9A
 * %E4%B8%BE%E7%9C%9F%E7%9A%84%E6%B2%A1%E6%9C%89%E9%82%A3%E4%B9%88%E7%AE%80%E5%8D%95.md
 **/
public class PizzaEnum {

    private PizzaStatus status;

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    @Test
    public void givenPizaOrder_whenReady_thenDeliverable() {
        PizzaEnum testPz = new PizzaEnum();
        testPz.setStatus(PizzaEnum.PizzaStatus.READY);
        //testPz.printTimeToDeliver();
        assertTrue(testPz.isDeliverable());

    }

    public enum PizzaStatus {
        /**
         *
         */
        ORDERED(5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY(2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }

        public boolean isOrdered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        public int getTimeToDelivery() {
            return timeToDelivery;
        }
    }
}