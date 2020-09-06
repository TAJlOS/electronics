<template>
  <div>
    <Header></Header>
    <div class="order-page">
      <div class="order-wrapper">
        <p class="p1">Ваш заказ:</p>
        <p class="p2">
          <span class="content">Состав: </span>
          <span class="order-content">{{ getOrderContent }}</span>
          <span class="order-sum"
            >{{ "(" + getOrderPrice + " "
            }}<span class="sign-ruble">&#8381;</span>)</span
          >
        </p>
        <p class="p3">Итого к оплате:</p>
        <p class="p4">
          <span class="order-sum"
            >{{ getOrderPrice + " "
            }}<span class="sign-ruble">&#8381;</span></span
          >
        </p>
        <div class="button-wrapper">
          <button @click="createOrder"><a>Оформить заказ</a></button>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "Order",
  components: { Footer, Header },
  created() {
    document.title = "Оформление заказа";
  },
  methods: {
    createOrder() {
      let order = {
        sum: this.$store.state.basket.order_sum,
        details: []
      };
      const products = this.$store.state.basket.basket_list;
      for (let index in products) {
        let product = products[index];
        let productId = product.id;
        let productCnt = this.$store.state.basket.cnt_list[productId];
        order.details.push({ id: productId, cnt: productCnt });
      }
      this.$store
        .dispatch("CREATE_ORDER_REQUEST", order)
        .then(() => {
          this.$store.commit("CLEAR_BASKET");
          this.$router.push("/order/successful");
        })
        .catch(() => {
          this.$router.push("/order/unsuccessful");
        });
    }
  },
  computed: {
    getOrderContent() {
      const cnt = this.$store.state.basket.order_product_cnt;
      const goods = cnt === 1 ? " товар " : " товаров ";
      return cnt + goods;
    },
    getOrderPrice() {
      let string = this.$store.state.basket.order_sum.toString();
      if (string.length <= 3) {
        return string;
      } else {
        let end = string.length - 3;
        return string.slice(0, end) + " " + string.slice(end, string.length);
      }
    },
    isUserAuthenticated() {
      return this.$store.state.isAuthenticated;
    }
  },
  watch: {
    isUserAuthenticated(newValue) {
      if (!newValue) {
        this.$router.push("/login");
      }
    }
  }
};
</script>

<style scoped>
.order-page {
  background: white;
  border: 1px solid #ddd;
  border-radius: 15px;
  width: 500px;
  margin: 100px auto;
  text-align: center;
}
.order-wrapper {
  display: inline-block;
  text-align: left;
  padding: 50px 0 50px 0;
}
.p1 {
  margin-bottom: 20px;
  font-size: 20px;
  font-weight: bold;
}
.p2 {
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 18px;
}
.content {
  font-weight: lighter;
  color: gray;
}
.order-sum {
  font-weight: bold;
}
.p3 {
  font-size: 18px;
  font-weight: bold;
}
.p4 {
  font-size: 22px;
}
.button-wrapper {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  margin: 50px 0 20px 0;
}
button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  font-size: 16px;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  display: inline-block;
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
  padding: 10px 20px 10px 20px;
  display: block;
  box-sizing: border-box;
}
button a:hover {
  color: white;
}

.sign-ruble {
  font-weight: lighter;
  color: gray;
}
</style>
