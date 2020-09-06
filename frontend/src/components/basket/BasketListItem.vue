<template>
  <div class="basket-list-item">
    <div class="basket-list-item-content">
      <div class="basket-list-item-img">
        <img class="basket-list-item-img-size" v-bind:src="image" />
      </div>
      <div class="basket-list-item-name">
        <p class="product-name">{{ getName() }}</p>
      </div>
      <div class="basket-list-item-counter">
        <button class="product-cnt-dec" @click="decProductCnt"><i>-</i></button>
        <input class="product-cnt-value" readonly :value="cnt" />
        <button class="product-cnt-inc" @click="incProductCnt"><i>+</i></button>
      </div>
      <div class="basket-list-item-price">
        <p class="product-price-value">
          {{ getPrice() }} <span class="sign-ruble">&#8381;</span>
        </p>
      </div>
    </div>
    <div class="basket-list-item-buttons">
      <button class="basket-list-item-buttons-remove" @click="delProduct">
        Удалить
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "BasketListItem",
  props: ["product"],
  data() {
    return {
      cnt: this.$store.state.basket.cnt_list[this.product.id],
      image: this.$store.state.REST_SERVER_URL + this.product.img
    };
  },
  methods: {
    getName() {
      return (
        "Процессор " + this.product.name
      );
    },
    incProductCnt() {
      this.$store.commit("INC_PRODUCT_CNT", this.product);
      this.cnt = this.$store.state.basket.cnt_list[this.product.id];
    },
    decProductCnt() {
      this.$store.commit("DEC_PRODUCT_CNT", this.product);
      this.cnt = this.$store.state.basket.cnt_list[this.product.id];
    },
    getPrice() {
      let string = (this.product.price * this.cnt).toString();
      if (string.length <= 3) {
        return string;
      } else {
        let end = string.length - 3;
        return string.slice(0, end) + " " + string.slice(end, string.length);
      }
    },
    delProduct() {
      this.$store.commit("DEL_PRODUCT", this.product);
    }
  }
};
</script>

<style scoped>
.basket-list-item {
  border-radius: 10px;
  background: white;
  margin: 20px;
  border: 1px solid rgb(209, 207, 207);
}
.basket-list-item-content {
  margin: 20px;
  display: flex;
  justify-content: flex-start;
}
.basket-list-item-img {
  align-self: flex-start;
  width: 100px;
  height: 100px;
}
.basket-list-item-img-size {
  width: 100px;
  height: 100px;
}
.basket-list-item-name {
  align-self: flex-start;
  font-size: 16pt;
  padding-right: 20px;
  padding-left: 20px;
  width: 60%;
}
.product-name {
  margin: 0;
}
.basket-list-item-counter {
  display: inline-flex;
  flex-direction: row;
}
.product-cnt-dec {
  border: 1px solid #ddd;
  color: #333;
  border-top-left-radius: 7px;
  border-bottom-left-radius: 7px;
  padding: 7px 10px 7px 10px;
  cursor: pointer;
  height: 38px;
  background: white;
  border-right-style: none;
  outline: none;
  box-sizing: border-box;
  vertical-align: middle;
}
.product-cnt-value {
  border: 1px solid #ddd;
  color: #333;
  padding: 7px 5px 7px 5px;
  background: white;
  border-left-style: none;
  border-right-style: none;
  display: inline-block;
  width: 20px;
  outline: none;
  height: 38px;
  box-sizing: border-box;
  text-align: center;
}
.product-cnt-inc {
  border: 1px solid #ddd;
  color: #333;
  border-top-right-radius: 7px;
  border-bottom-right-radius: 7px;
  padding: 7px 10px 7px 10px;
  cursor: pointer;
  display: inline-block;
  background: white;
  border-left-style: none;
  outline: none;
  height: 38px;
  box-sizing: border-box;
  vertical-align: middle;
}
button:hover {
  border-color: #aaa;
  color: #aaa;
}

i {
  font-size: larger;
}

.basket-list-item-price {
  font-size: 16pt;
  text-align: right;
  margin-left: auto;
  margin-right: 0;
  font-weight: bold;
  width: 150px;
}

.product-price-value {
  margin: 0;
}

.sign-ruble {
  font-weight: lighter;
}
.basket-list-item-buttons {
  margin: 20px;
  text-align: right;
}
.basket-list-item-buttons-remove {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  padding: 10px 20px 10px 20px;
  border-radius: 5px;
  color: black;
  cursor: pointer;
}
.basket-list-item-buttons-remove:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
  color: white;
}

.basket-list-item-buttons-remove:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}
</style>
