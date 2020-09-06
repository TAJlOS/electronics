<template>
  <div class="sorting">
    <div class="sorting-wrapper">
      <a>Сортировать:</a>
      <a class="sorting-name" @click="changeFlag">
        <span class="sorting-name-value" :class="{ active: flag }">{{
          selected
        }}</span>
        <span :class="{ 'arrow up': flag, 'arrow down': !flag }"></span>
      </a>
      <div v-if="flag" class="dropdown">
        <ul class="dropdown-list">
          <li
            v-for="value in this.list"
            v-bind:key="value"
            @click="setChecked(value)"
          >
            <input
              type="radio"
              v-model="selected"
              v-bind:id="value"
              v-bind:value="value"
            /><label>{{ value }}</label>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Sorting",
  data() {
    return {
      flag: null,
      list: ["По возрастанию цены", "По убыванию цены", "По наименованию"],
      selected: "По возрастанию цены"
    };
  },
  created() {
    this.selected = this.list[this.$store.state.catalog.order];
  },
  methods: {
    changeFlag: function() {
      this.flag = !this.flag;
    },
    setChecked: function(value) {
      this.selected = value;
      this.flag = !this.flag;
      this.$store.commit("SET_SORTING_FLAG", this.list.indexOf(this.selected));
      this.$store.commit("TO_PAGE", 1);
      this.$store.dispatch("GET_PRODUCT_LIST");
    }
  }
};
</script>

<style scoped>
.sorting {
  border-radius: 10px;
  border-color: rgb(206, 201, 201);
  border-width: 1px;
  border-style: solid;
  margin: 20px;
  padding: 20px;
  width: 270px;
  font-size: 16px;
}
.sorting-wrapper {
  display: flex;
  flex-direction: row;
  position: relative;
}
a {
  font-size: 14px;
}
span {
  font-size: 14px;
}
ul {
  margin: 0;
  padding: 0 10px;
}
li {
  list-style-type: none;
  padding: 10px 0;
}
li:hover {
  background-color: lightcoral;
}
input[type="checkbox"] {
  transform: scale(1.3);
  margin: 7px 7px 7px 10px;
  border-radius: 20px;
}
.sorting-name {
  display: block;
  cursor: pointer;
  user-select: none;
  text-align: left;
  margin-left: 5px;
  color: royalblue;
}
.sorting-name-value {
  margin-right: 5px;
  text-transform: lowercase;
}
.active {
  color: rgb(216, 42, 42);
}
.arrow {
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
}

.up {
  transform: rotate(-135deg);
  -webkit-transform: rotate(-135deg);
  border-color: rgb(216, 42, 42);
}

.down {
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  border-color: royalblue;
}
.dropdown {
  position: absolute;
  border-radius: 10px;
  border-color: rgb(206, 201, 201);
  border-width: 1px;
  border-style: solid;
  margin-top: 20px;
  margin-left: -30px;
  margin-right: -30px;
  width: calc(270px + 30px + 30px);
  box-sizing: border-box;
  padding: 5px;
  background: white;
  z-index: 1;
}
</style>
