<template>
<div>
<br>
    <h1>Add Products </h1>

    <div>
        <b-row>
            <b-col sm="6">
    <b-dropdown id="dropdown-form" text="Add A New Product" ref="dropdown" class="m-2">
      <b-dropdown-form>
        <b-form-group>
            <b-form-select v-model="selected" class="mb-3 category">
                <option :value="null">Select Category</option>
                <option value="Clothing">Clothing</option>
                <option value="Electronics">Electronics</option>
                <option value="Sports">Sports</option>
            </b-form-select>
            
        </b-form-group>
        <b-form-group v-if="selected == 'Electronics'"> 
        

            <b-form-select v-model="subcategory" class="mb-3 subcategory">
                <option :value="null">Select Sub-Category</option>
                <option value="Phones">Phones</option>
                <option value="Tablets">Tablets</option>
                <option value="Gadgets">Gadgets</option>
            </b-form-select>
        </b-form-group>
        <b-form-group label="SubCategories" v-if="selected == 'Sports'" label-for="SubCategories" @submit.stop.prevent>
            
            <b-form-select v-model="subcategory" class="mb-3 subcategory">
                <option :value="null">Select Sub-Category</option>
                <option value="Cricket">Cricket</option>
                <option value="Football">Football</option>
                <option value="Basketball">Basketball</option>
            </b-form-select>
        </b-form-group>

        <b-form-group label="SubCategories" v-if="selected == 'Clothing'" label-for="SubCategories" @submit.stop.prevent>

            <b-form-select v-model="subcategory" class="mb-3 subcategory">
                <option :value="null">Select Sub-Category</option>
                <option value="Men">Men</option>
                <option value="Women">Women</option>
                <option value="Kids">Kids</option>
            </b-form-select>
        </b-form-group>

        <b-form-group v-if="subcategory!=null" label="Product Name" label-for="product-name">
          <b-form-input
            id="product-name"
            type="text"
            size="sm">
          </b-form-input>
        </b-form-group>

        
        <!-- <b-form-checkbox class="mb-3">Remember me</b-form-checkbox>
        <b-button variant="primary" size="sm" @click="onClick">Sign In</b-button> -->
      </b-dropdown-form>
      <b-dropdown-divider></b-dropdown-divider>
      
    </b-dropdown>
            </b-col>
            <b-col>
                
                <div v-if="subcategory!=null">
                    <b-card class="detailsform">
                    <h2> Enter the details </h2>
                    <b-row>
                        <b-col>
                            <label for="title">Title</label>
                        </b-col>
                        <b-col>
                            <label for="value">Value</label>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col sm="5">
                            
                            
                            <b-form-input id="title" type="text"></b-form-input>
                        </b-col>
                        <b-col>
                            
                            <b-form-input id="value" type="text"></b-form-input>
                    
                        </b-col>
                    </b-row>
                    <!-- <b-row>
                    <b-col sm="5">
                            
                            
                            <b-form-input id="title" type="text"></b-form-input>
                        </b-col>
                        <b-col>
                            
                            <b-form-input id="value" type="text"></b-form-input>
                    
                        </b-col>
                    </b-row> -->
                    <br><br>
                    <b-row>
                        <b-col sm="3">
                            <label class="label" for="price">Price:</label>
                        </b-col>
                        <b-col>
                            <b-form-input id="price" type="text"></b-form-input>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col sm="3">
                            <label class="label" for="stock">Stock:</label>
                        </b-col>
                        <b-col>
                            <b-form-input id="stock" type="text"></b-form-input>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col>
                        <b-button size="lg" variant="primary">Save</b-button>
                        </b-col>
                    </b-row>
                    </b-card>
                </div>
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
            selected: null,
            subcategory: null,
            attribute: true
        }
    },
    methods: {
        addAtt(e) {
            e.prevent.default();
            this.attribute= true;
        }
    }
}
</script>
<style scoped>
#dropdown-form{
    width:40%;
}
.category{
    width:300px;
}
.attributeButton{
   text-decoration: underline;
   font-weight: bold;
    margin-top: 4%;
    margin-bottom: 4%;
}
.label{
    font-size:25px;
    font-weight: bold;
}
</style>
