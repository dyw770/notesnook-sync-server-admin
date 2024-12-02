<template>
  <div>
    <el-form
             label-position="left">
      <el-form-item prop="username">
        <el-icon>
          <User/>
        </el-icon>
        <el-input v-model="loginForm.username" name="username" type="text" placeholder="username"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-icon>
          <Lock/>
        </el-icon>
        <el-input
            type="password"
            v-model="loginForm.password"
            name="password"
            placeholder="password"
            :show-password="true"
            @keyup.enter.native="handleLogin"/>
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="primary" @click.native.prevent="handleLogin">
          Sign in
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>

import {ref} from "vue";
import {Lock, User} from '@element-plus/icons-vue'
import router from "@/router";
import {login} from "@/api/user";
import {ElMessage} from "element-plus";

const loginForm = ref<{username: string, password: string}>({username: 'admin', password: 'admin'});
const loading = ref(false);


const handleLogin = () => {
  login(loginForm.value.username, loginForm.value.password)
      .then(() => {
        ElMessage.success("Login successful");
        router.push('/');
      }).catch(err => {
        console.log(err);
      })
}


</script>