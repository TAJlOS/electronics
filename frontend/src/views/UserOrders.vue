<template>
  <div>
    <Header></Header>
    <OrderList></OrderList>
    <Footer></Footer>
  </div>
</template>

<script>
import OrderList from "@/components/profile/OrderList";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "UserOrders",
  components: { Footer, Header, OrderList },
  created() {
    document.title = "История заказов";
  },
  computed: {
    isUserAuthenticated() {
      return this.$store.state.isAuthenticated;
    }
  },
  watch: {
    isUserAuthenticated(newValue) {
      if (!newValue) {
        this.$router.push("/");
      }
    }
  }
};
</script>

<style scoped></style>
