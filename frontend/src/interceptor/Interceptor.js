import axios from 'axios';
import authService from '../services/AuthService';

const httpClient = axios.create({baseURL: 'http://localhost:8081'});

httpClient.interceptors.request.use(
    async (config) => {
        const token = await authService.getToken();
        config.headers['Authorization'] = `Bearer ${token}`;
        
        // config.headers['Access-Control-Allow-Origin'] = '*'; // Allow all origins
        // config.headers['Access-Control-Allow-Methods'] = 'GET, POST, PUT, DELETE, OPTIONS';
        // config.headers['Access-Control-Allow-Headers'] = 'Origin, X-Requested-With, Content-Type, Accept, Authorization';
        // config.headers['Access-Control-Allow-Credentials'] = 'true';
        return config;
    },
    (error) => {
        console.error('Error setting Authorization header:', error);
        return Promise.reject(error);
    }
);

export default httpClient;