<template>
  <div>
    <div v-if="PRODUCT_LIST.length !== 0">
      <ProductListItem
        v-for="product in PRODUCT_LIST"
        v-bind:key="product.id"
        v-bind:product="product"
      >
      </ProductListItem>
    </div>
    <div v-else>
      <EmptyProductList></EmptyProductList>
    </div>
  </div>
</template>

<script>
import ProductListItem from "@/components/product-page/ProductListItem";
import { mapGetters } from "vuex";
import EmptyProductList from "@/components/product-page/EmptyProductList";
export default {
  name: "ProductList",
  components: { EmptyProductList, ProductListItem },
  computed: {
    ...mapGetters(["PRODUCT_LIST"])
  }
};
</script>

<style scoped></style>
