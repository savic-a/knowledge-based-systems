import httpClient from "../interceptor/Interceptor";

class ClientService {

    async getCardBalance(clientId) {
        try {
            const response = await httpClient.get(`/credit-card/1`);
            return response.data;
        } catch (error) {
            console.error('Error fetching credit cards:', error);
            throw error;
        }
    };

    async getFinancialGoal(clientId) {
        try {
            const response = await httpClient.get(`/financial-goal/1`);
            console.log(response)
            return response.data;
        } catch (error) {
            console.error('Error fetching credit cards:', error);
            throw error;
        }
    };
}

const clientService = new ClientService();
export default clientService;
