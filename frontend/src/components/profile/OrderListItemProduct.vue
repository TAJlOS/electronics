<template>
  <div>
    <div class="product-content">
      <div class="product-img">
        <img :src="image" />
      </div>
      <div class="product-info">
        <p class="product-name">{{ this.product.name }}</p>
        <p class="product-price">
          {{ getPrice() + " " }}<span class="sign-ruble">&#8381;</span>
          <span class="amount">Количество: </span>
          <span class="product-amount">{{ this.product.amount + " шт." }}</span>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderListItemProduct",
  props: ["product"],
  data() {
    return {
      image: this.$store.state.REST_SERVER_URL + this.product.img
    };
  },
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
.product-content {
  display: inline-flex;
  flex-direction: row;
  padding-bottom: 20px;
}
.product-img {
  width: 100px;
  height: 100px;
}
.product-img img {
  width: 100%;
  height: 100%;
}
.product-info {
  padding-top: 0;
  padding-left: 20px;
  padding-right: 20px;
}
.product-price {
  font-weight: bold;
}
.product-name {
  margin-top: 0;
}
.amount {
  padding-left: 100px;
  color: gray;
  font-weight: lighter;
}
.product-amount {
  font-weight: normal;
}
</style>
