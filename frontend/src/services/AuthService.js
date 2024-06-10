import httpClient from "../interceptor/Interceptor";

class AuthService {
  
    async login(email, password) {
      try {
            const response = await httpClient.post('http://localhost:8081/client/login',{
                email: email,
                password: password
            });
            await this.setToken(response.data['accessToken']);
            console.log(response.data['accessToken'])
            return true;

        } catch (error) {
            console.error('Invalid email or password!');
            return false;
        }
    }
  
    async setToken(token) {
      localStorage.setItem('jwt', token);
    }

    getToken() {
        const user = localStorage.getItem('jwt');
        return user;
    }

    async getUserDetails() {
        try {
            const response = await httpClient.get('http://localhost:8081/client/current');
            console.log(response)
            return response.data;

        } catch (error) {
            console.error("Current user does'n exist!");
            return false;
        }
    }

    logOut() {
        localStorage.removeItem('jwt');
    }
}
  
  const authService = new AuthService();
  
  export default authService;