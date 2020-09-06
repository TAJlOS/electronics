package electronics.backend.exception;

public class ProductNotFoundException extends RuntimeException {
    private final Long productId;

    public Long getProductId() {
        return productId;
    }

    public ProductNotFoundException(Long id) {
        this.productId = id;
    }
}
