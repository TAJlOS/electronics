<template>
  <div class="filter">
    <a class="filter-name" @click="changeFlag">
      <i v-bind:class="[flag ? 'arrow down' : 'arrow up']"></i>
      <span
        ref="#"
        v-bind:style="{ 'font-weight': [flag ? 'bold' : 'normal'] }"
        >{{ this.parameter.name }}</span
      >
    </a>
    <div v-if="flag" class="checkbox-filter">
      <ul class="filter-checkbox-list">
        <li
          v-for="value in this.parameter.values"
          v-bind:key="value.name"
          @click="setChecked(value.name)"
        >
          <input
            type="checkbox"
            v-model="checkedList"
            v-bind:id="value.name"
            v-bind:value="value.name"
          /><label>{{ getValueName(value.name) }}</label>
          <span class="filter-value-cnt">
            {{ value.cnt !== null ? "(" + value.cnt + ")" : "" }}</span
          >
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "CheckboxFilter",
  props: ["parameter"],
  data() {
    return {
      flag: null
    };
  },
  created() {
    this.flag = this.$store.state.left_page.parameters_state[
      this.parameter.name
    ].open;
  },
  computed: {
    checkedList: {
      get() {
        return this.$store.state.left_page.parameters_state[this.parameter.name]
          .checkedList;
      },
      set() {}
    }
  },
  methods: {
    changeFlag: function() {
      this.flag = !this.flag;
      this.$store.commit("SET_FILTER_FLAG", {
        name: this.parameter.name,
        flag: this.flag
      });
    },
    setChecked: function(value) {
      let indexOf = this.$store.state.left_page.parameters_state[
        this.parameter.name
      ].checkedList.indexOf(value);
      if (indexOf !== -1) {
        this.$store.commit("REMOVE_VALUE_IN_CHECKED_LIST", {
          name: this.parameter.name,
          value: value
        });
      } else {
        this.$store.commit("ADD_VALUE_IN_CHECKED_LIST", {
          name: this.parameter.name,
          value: value
        });
      }
    },
    getValueName(value) {
      if (value === "true") return "есть";
      if (value === "false") return "нет";
      return value;
    }
  }
};
</script>

<style scoped>
.filter {
  padding: 0;
  width: 100%;
  box-sizing: border-box;
}
.filter-value-cnt {
  font-weight: lighter;
  color: gray;
}
.filter-name {
  display: block;
  cursor: pointer;
  padding: 8px 0 8px 15px;
  user-select: none;
  text-align: left;
}
.filter-name:hover {
  background-color: lightcoral;
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
}

.down {
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
}
.filter li:hover {
  background-color: lightcoral;
}
.checkbox-filter {
  display: flex;
  text-align: left;
}
.filter-checkbox-list {
  padding-left: 15px;
  width: 100%;
  justify-content: flex-start;
  box-sizing: border-box;
}

.filter-checkbox-list input[type="checkbox"] {
  transform: scale(1.3);
  margin: 7px 7px 7px 10px;
}
li {
  list-style-type: none;
}

ul {
  margin-left: 0;
  padding-left: 0;
}
span {
  padding-left: 5px;
}
</style>
