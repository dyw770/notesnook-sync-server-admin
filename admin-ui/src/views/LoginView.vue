<template>
  <div class="login">
    <div class="login-box">
      <div class="login-logo">Login</div>
      <el-form label-position="left" size="large">
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            name="username"
            type="text"
            :prefix-icon="User"
            placeholder="username" />
        </el-form-item>
        <el-form-item prop="password">
         
          <el-input
            type="password"
            :prefix-icon="Lock"
            v-model="loginForm.password"
            name="password"
            placeholder="password"
            :show-password="true"
            @keyup.enter.native="handleLogin" />
        </el-form-item>
        <el-form-item>
          <el-button
            :loading="loading"
            type="primary"
            class="login-btn"
            @click.native.prevent="handleLogin">
            Sign in
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {Lock, User} from '@element-plus/icons-vue'
import router from '@/router'
import {login} from '@/api/user'
import {ElMessage} from 'element-plus'

const loginForm = ref<{ username: string; password: string }>({
  username: 'admin',
  password: 'admin'
})
const loading = ref(false)

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

<style scoped>
.login{
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, #4facfe, #00f2fe);
}
.login-logo{
  font-size: 2rem;
  font-weight: bold;
  color: #4facfe;
  margin-bottom: 20px;
}
.login-box {
  width: 400px;
  background: #fff;
  padding: 40px 30px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}
.login-btn{
  width: 100%;
}
</style>
