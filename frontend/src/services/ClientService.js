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
            console.error('Error fetching financial goal:', error);
            throw error;
        }
    };

    async getTransactions(clientId) {
        try {
            const response = await httpClient.get(`/transaction/1`);
            console.log(response)
            return response.data;
        } catch (error) {
            console.error('Error fetching transaction:', error);
            throw error;
        }
    };

    async addFinancialGoal(clientId, goal) {
        try {
            const response = await httpClient.post(`/financial-goal/5`, goal);
            console.log(response)
            return response.data;
        } catch (error) {
            console.error('Error adding new financial goal:', error);
            throw error;
        }
    };
}

const clientService = new ClientService();
export default clientService;
