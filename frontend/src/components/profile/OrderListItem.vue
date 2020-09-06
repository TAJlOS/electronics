<template>
  <div class="order-list-item">
    <div class="order-title">
      {{ "Заказ " + this.order.id + " от "+this.order.registration }}
    </div>
    <div class="order-content">
      <div class="product-list">
        <OrderListItemProduct
          v-for="product in this.order.products"
          v-bind:key="product.id"
          v-bind:product="product"
        >
        </OrderListItemProduct>
      </div>
      <div class="order-info">
        <p class="order-status">
          Статус:
          <span class="order-status-value">{{ this.order.status }}</span>
        </p>
        <p class="order-sum">
          Итого: <span class="order-sum-value">{{ getSum() + " " }}</span
          ><span class="sign-ruble">&#8381;</span>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import OrderListItemProduct from "@/components/profile/OrderListItemProduct";
export default {
  name: "OrderListItem",
  components: { OrderListItemProduct },
  props: ["order"],
  methods: {
    getSum() {
      let string = this.order.sum.toString();
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
.order-list-item {
  border-radius: 10px;
  background: white;
  margin: 20px;
  display: inline-flex;
  flex-direction: column;
  border: 1px solid rgb(209, 207, 207);
  text-align: left;
}
.order-title {
  background: lightgray;
  padding: 15px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.order-content {
  display: flex;
  flex-direction: row;
  padding: 20px;
}
.product-list {
  display: flex;
  flex-direction: column;
}
.order-info {
  padding-top: 0;
  padding-left: 20px;
  padding-right: 20px;
}
.order-status {
  margin-top: 0;
  color: gray;
  font-weight: lighter;
}
.order-status-value {
  font-weight: normal;
  color: black;
}
.order-sum {
  font-weight: bold;
  font-size: 20px;
}
.order-sum-value {
  color: gray;
}
.sign-ruble {
  color: gray;
}
</style>
