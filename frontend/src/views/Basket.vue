<template>
  <div>
    <Header></Header>
    <BasketList></BasketList>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import BasketList from "@/components/basket/BasketList";
export default {
  name: "Basket",
  components: { Footer, Header, BasketList },
  created() {
    document.title = "Корзина";
  }
};
</script>

<style scoped></style>
