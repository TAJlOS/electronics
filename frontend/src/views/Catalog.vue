<template>
  <div>
    <Header></Header>
    <div id="product-page-content">
      <LeftPage></LeftPage>
      <div class="product-page-list">
        <ProductList></ProductList>
        <Pagination></Pagination>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import LeftPage from "@/components/left-page/LeftPage";
import ProductList from "@/components/product-page/ProductList";
import Pagination from "@/components/product-page/Pagination";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: "Catalog",
  components: { Footer, Header, Pagination, ProductList, LeftPage },
  created() {
    document.title = "Каталог";
  }
};
</script>

<style scoped>
#product-page-content {
  display: flex;
  justify-content: center;
  align-content: space-between;
  align-items: flex-start;
  flex-direction: row;
}

.product-page-list {
  max-width: 900px;
  display: flex;
  flex-direction: column;
}
</style>
