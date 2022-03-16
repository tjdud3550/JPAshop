package jpabook.jpashop.exception;

public class NotEnoughStockException extends  RuntimeException{


    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }


}
//==override 하는이유 메세지 다 넘겨서 + 예외 발생한 근원전 exception 나타나려고 /

