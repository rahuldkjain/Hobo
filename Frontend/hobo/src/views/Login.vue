<template>
  <div >
      <br>
      <b-row>
          
            <b-col sm="6">
            <b-card bg-variant="light" class="text-center">
            <h4>Login</h4>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group
                id="input-group-1"
                label="Email address:"
                label-for="input-1">
    
            <b-form-input
                size="4"  
                id="input-1"
                v-model="form.email"
                type="email"
                required
                placeholder="Enter email">
            </b-form-input>

        </b-form-group>

        <b-form-group id="input-group-2" label="Your Password:" label-for="input-2">
            <b-form-input
            id="input-2"
            v-model="form.name"
            required
            placeholder="Enter password">
        </b-form-input>
        </b-form-group>

      

        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </b-card>

        <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
        </b-card>
      </b-col>
        
          
      <b-col sm="5">
      <b-card bg-variant="light" class="text-center">
          <h4>Signin</h4>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
             <b-form-group
                id="name"
                label="Name:"
                label-for="name">
    
            <b-form-input
                size="4"  
                id="name"
                v-model="form.name"
                type="text"
                required
                placeholder="Enter name">
            </b-form-input>

        </b-form-group>
            
            <b-form-group
                id="input-group-1"
                label="Email address:"
                label-for="input-1">
    
            <b-form-input
                size="4"  
                id="input-1"
                v-model="form.email"
                type="email"
                required
                placeholder="Enter email">
            </b-form-input>

        </b-form-group>

      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          required
          placeholder="Enter name"
        ></b-form-input>
      </b-form-group>

      

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
      </b-card>

    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>
      </b-col>
          
      </b-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          email: '',
          name: '',
        //   food: null,
        //   checked: []
        },
        foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
        show: true
      }
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.food = null
        this.form.checked = []
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
</script>
<style scoped>

</style>


