<template>
  <div class="product-price">
    <p class="product-price-value">
      {{ getPrice() }} <span class="sign-ruble">&#8381;</span>
    </p>
  </div>
</template>

<script>
export default {
  name: "ProductPrice",
  props: ["product"],
  methods: {
    getPrice() {
      let string = this.product.price.toString();
      if (string.length <= 3) {
        return string;
      } else {
        let end = string.length - 3;
        return string.slice(0, end) + " " + string.slice(end, string.length);
      }
    }
  }
};
</script>

<style scoped>
.product-price {
  padding-left: 70px;
  font-size: 16pt;
  margin-left: auto;
  font-weight: bold;
}

.product-price-value {
  margin: 0;
}

.sign-ruble {
  font-weight: lighter;
}
</style>
