<template>
  <div class="order-accept-page">
    <div class="order-price">
      <p>
        Итого: {{ ORDER_PRODUCT_CNT }} товара на {{ ORDER_SUM }}
        <span class="sign-ruble">&#8381;</span>
      </p>
    </div>
    <div class="order-button-accept">
      <button><router-link to="/order">Оформить заказ</router-link></button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "OrderAcceptPage",
  computed: {
    ...mapGetters(["ORDER_PRODUCT_CNT", "ORDER_SUM"])
  }
};
</script>

<style scoped>
.order-accept-page {
  margin-left: 20px;
  margin-top: 20px;
  display: inline-flex;
  flex-direction: column;
}
.order-price {
  display: inline-block;
  background: white;
  border: 1px solid #ddd;
  border-radius: 10px;
}
p {
  font-weight: bold;
  font-size: 20px;
  margin: 30px 50px;
}
button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  padding: 0;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
  margin-top: 20px;
  width: 100%;
}
button:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
}
button:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}

button a:hover {
  color: white;
}
a {
  text-decoration: none;
  color: black;
  padding: 10px 20px 10px 20px;
  display: block;
  box-sizing: border-box;
}
.order-button-accept {
  display: inline-block;
}
</style>
