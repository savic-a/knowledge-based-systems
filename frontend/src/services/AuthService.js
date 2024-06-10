import httpClient from "../interceptor/Interceptor";
import {jwtDecode } from 'jwt-decode';

class AuthService {
  
    async login(email, password) {
      try {
            const response = await httpClient.post('http://localhost:8081/client/login',{
                email: email,
                password: password
            });
            await this.setToken(response.data['accessToken']);
            return true;

        } catch (error) {
            console.error('Invalid email or password!');
            return false;
        }
    }
  
    async setToken(user) {
      localStorage.setItem('jwt', JSON.stringify(user));
    }

    getToken() {
        const user = JSON.parse(localStorage.getItem('jwt'));
        return user;
    }

    getUserDetails() {
        const token = this.getToken();
        if (!token) {
            return null;
        }

        try {
            const decodedToken = jwtDecode(token);
            return {
                email: decodedToken.sub,
                name: decodedToken.name,
                surname: decodedToken.surname
            };
        } catch (error) {
            console.error('Invalid token:', error);
            return null;
        }
    }

    logOut() {
        localStorage.removeItem('jwt');
    }
}
  
  const authService = new AuthService();
  
  export default authService;