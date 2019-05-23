<template>
<div>
<br>
    <h1>Add Products </h1>

    <div>
        <b-row>
            <b-col>
    <b-dropdown id="dropdown-form" text="Add A New Product" ref="dropdown" class="m-2">
      <b-dropdown-form>
        <b-form-group label="Categories" label-for="categories" @submit.stop.prevent>
            <b-form-radio v-model="selected" name="Categories" value="Electronics">Electronics</b-form-radio>
            <b-form-radio v-model="selected" name="Categories" value="Sports">Sports</b-form-radio>
            <b-form-radio v-model="selected" name="Categories" value="Clothing">Clothing</b-form-radio>
            <!-- {{selected}} -->
        </b-form-group>
        <b-form-group label="SubCategories" v-if="selected == 'Electronics'" label-for="SubCategories" @submit.stop.prevent>
            <b-form-radio name="SubCategories" value="Phones">Phones</b-form-radio>
            <b-form-radio name="SubCategories" value="Tablets">Tablets</b-form-radio>
            <b-form-radio name="SubCategories" value="Gadgets">Gadgets</b-form-radio>
            <!-- {{selected}} -->
        </b-form-group>
        <b-form-group label="SubCategories" v-if="selected == 'Sports'" label-for="SubCategories" @submit.stop.prevent>
            <b-form-radio name="SubCategories" value="Sports Wear">Sports Wear</b-form-radio>
            <b-form-radio name="SubCategories" value="Bat">Bat</b-form-radio>
            <b-form-radio name="SubCategories" value="Badminton">Badminton</b-form-radio>
            <!-- {{selected}} -->
        </b-form-group>

        <b-form-group label="SubCategories" v-if="selected == 'Clothing'" label-for="SubCategories" @submit.stop.prevent>
            <b-form-radio name="SubCategories" value="Jeans">Jeans</b-form-radio>
            <b-form-radio name="SubCategories" value="Tops">Tops</b-form-radio>
            <b-form-radio name="SubCategories" value="T shirts">T shirts</b-form-radio>
            <!-- {{selected}} -->
        </b-form-group>

        <!-- <b-form-group label="Password" label-for="dropdown-form-password">
          <b-form-input
            id="dropdown-form-password"
            type="password"
            size="sm"
            placeholder="Password"
          ></b-form-input>
        </b-form-group> -->

        <b-form-checkbox class="mb-3">Remember me</b-form-checkbox>
        <b-button variant="primary" size="sm" @click="onClick">Sign In</b-button>
      </b-dropdown-form>
      <b-dropdown-divider></b-dropdown-divider>
      <!-- <b-dropdown-item-button>New around here? Sign up</b-dropdown-item-button>
      <b-dropdown-item-button>Forgot Password?</b-dropdown-item-button> -->
    </b-dropdown>
            </b-col>
        </b-row>
  </div>
</div>
</template>
<script>
export default {
    name: 'AddProduct',
    data() {
        return {
            selected: ''
        }
    }
}
</script>
<style scoped>

</style>


