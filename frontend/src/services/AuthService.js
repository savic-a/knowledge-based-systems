import httpClient from "../interceptor/Interceptor";

class AuthService {
  
    async login(email, password) {
      try {
            const response = await httpClient.post('http://localhost:8081/client/login',{
                email: email,
                password: password
            });
            // await this.setToken(response.data['accessToken']);
            console.log(response)
            return response;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    }
  
    async setToken(user) {
      localStorage.setItem('jwt', JSON.stringify(user));
    }

    getToken() {
        const user = JSON.parse(localStorage.getItem('jwt'));
        return user;
    }
}
  
  const authService = new AuthService();
  
  export default authService;