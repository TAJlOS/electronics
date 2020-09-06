<template>
  <div>
    <div class="product-image">
      <img class="product-img-size" v-bind:src="image" />
    </div>
    <div class="product-title">
      <p class="product-name">{{ getName() }}</p>
      <p class="product-specification">{{ getSpecification() }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProductInfo",
  props: ["product"],
  data() {
    return {
      image: this.$store.state.REST_SERVER_URL + this.product.img
    };
  },
  methods: {
    getSpecification() {
      return (
        "[" +
        this.product.socket +
        ", " +
        this.product.n_thread +
        " x " +
        this.product.base_frequency +
        " МГц" +
        ", L3 - " +
        this.product.l3_cache +
        " МБ, " +
        (this.product.graphic_card != null
          ? this.product.graphic_card + " ,"
          : "") +
        "TDP " +
        this.product.tdp +
        " Вт" +
        "]"
      );
    },
    getName() {
      return (
        "Процессор " + this.product.name
      );
    }
  }
};
</script>

<style scoped>
.product-image {
  justify-content: flex-start;
  width: 100px;
  height: 100px;
  margin-right: 20px;
}

.product-img-size {
  width: 100px;
  height: 100px;
}

.product-title {
  justify-content: flex-start;
  font-size: 16pt;
  padding-right: 20px;
}

.product-name {
  font-size: larger;
  margin: 0;
}

.product-specification {
  font-size: medium;
}
</style>
