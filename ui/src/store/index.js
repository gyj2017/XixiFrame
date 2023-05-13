import { createStore } from 'vuex'
import router from "@/router";
export default createStore({
  state: {
  },
  getters: {
    GET_TOKEN:state =>{
      return sessionStorage.getItem("token");
    },
    GET_MENU_LIST:state =>{
      return JSON.parse(sessionStorage.getItem("menuList"));
    },
    GET_USER_INFO:state =>{
      return JSON.parse(sessionStorage.getItem("userInfo"));
    }
  },
  mutations: {
    SET_TOKEN:(state, token)=>{
      sessionStorage.setItem("token", token);
    },
    SET_MENU_LIST:(state, menuList)=>{
      sessionStorage.setItem("menuList", JSON.stringify(menuList))
    },
    SET_USER_INFO:(state, userInfo)=>{
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
    }
  },
  actions: {
    logout(){
      window.sessionStorage.clear();
      router.replace("/login")
    }

  },
  modules: {
  }
})
