<template>
  <div class="product-list-item">
    <div class="product-main">
      <ProductInfo class="product-info" v-bind:product="product"></ProductInfo>
      <ProductPrice v-bind:product="product"></ProductPrice>
    </div>
    <ProductButtons v-bind:product="product"></ProductButtons>
  </div>
</template>

<script>
import ProductInfo from "@/components/product-page/ProductInfo";
import ProductPrice from "@/components/product-page/ProductPrice";
import ProductButtons from "@/components/product-page/ProductButtons";

export default {
  name: "ProductListItem",
  components: { ProductButtons, ProductPrice, ProductInfo },
  props: ["product"]
};
</script>

<style scoped>
.product-list-item {
  border-radius: 10px;
  background: white;
  margin: 20px;
  border: 1px solid rgb(209, 207, 207);
}

.product-main {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 20px;
}

.product-info {
  align-self: flex-start;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  max-width: 600px;
}
</style>
