<template>
  <div class="product-buttons">
    <button v-if="!isProductInBasket" @click="click">
      <a>Купить</a>
    </button>
    <button v-else>
      <a>В корзине</a>
    </button>
  </div>
</template>

<script>
export default {
  name: "ProductButtons",
  props: ["product"],
  data() {
    return {
      flag: true
    };
  },
  methods: {
    click: function() {
      if (this.flag) {
        this.$store.commit("ADD_PRODUCT_TO_BASKET", this.product);
        this.flag = false;
      }
    }
  },
  computed: {
    isProductInBasket: function() {
      let data = this.$store.state.basket.basket_list;
      for (let index in data) {
        let product = data[index];
        if (product.id === this.product.id) {
          return true;
        }
      }
      return false;
    }
  }
};
</script>

<style scoped>
.product-buttons {
  padding: 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  height: 50px;
  width: 150px;
  font-size: 16px;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
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
a {
  text-decoration: none;
  color: black;
  padding: 10px 20px;
  display: block;
  box-sizing: border-box;
}
a:hover {
  color: white;
}
</style>
