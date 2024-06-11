import httpClient from "../interceptor/Interceptor";

class ClientService {

    async getCardBalance() {
        try {
            const response = await httpClient.get(`/credit-card/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching credit cards:', error);
            throw error;
        }
    };

    async getFinancialGoal() {
        try {
            const response = await httpClient.get(`/financial-goal/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching financial goal:', error);
            throw error;
        }
    };

    async getTransactions() {
        try {
            const response = await httpClient.get(`/transaction/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching transaction:', error);
            throw error;
        }
    };

    async addFinancialGoal(goal) {
        try {
            const response = await httpClient.post(`/financial-goal/${localStorage.getItem('id')}`, goal);
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
