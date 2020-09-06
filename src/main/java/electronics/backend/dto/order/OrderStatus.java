package electronics.backend.dto.order;

public enum OrderStatus {
    PAID("оплачено"),
    NOT_PAID("не оплачено");

    private final String status;

    OrderStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
