<template>
    <div class="container mx-auto px-4">
      <h1 class="text-3xl font-bold mb-8">Historial</h1>
  
      <!-- Tabs para dividir las vistas -->
      <div class="flex justify-around border-b mb-4">
        <button 
          v-for="tab in tabs"
          :key="tab.name"
          @click="setActiveTab(tab.name)"
          class="px-4 py-2 text-lg"
          :class="activeTab === tab.name ? 'border-b-2 border-blue-500 text-blue-500' : 'text-gray-500'"
        >
          {{ tab.label }}
        </button>
      </div>
  
      <!-- Vista para Órdenes -->
      <div v-if="activeTab === 'ordenes'">
        <h2 class="text-2xl font-semibold mb-4">Órdenes</h2>
        <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
          <div
            v-for="order in paginatedOrdenes"
            :key="order.id_orden"
            class="border border-gray-300 rounded-lg shadow-md p-4"
          >
            <h2 class="text-xl font-semibold mb-2">Orden #{{ order.id_orden }}</h2>
            <hr class="my-2 border-gray-400">
            <p class="text-lg">Fecha: {{ formatDate(order.fecha_orden) }}</p>
            <p :class="['font-medium', getStateColor(order.estado)]">Estado: {{ order.estado }}</p>
            <p class="text-lg font-bold">Total: ${{ order.total }}</p>
            <button @click="openModal(order.id_orden)" class="bg-blue-500 text-white px-4 py-2 rounded">
              Detalle de la Orden
            </button>
          </div>
        </div>
      </div>


      <!-- Modal con los detalles de la orden -->
    <div v-if="isModalOpen" class="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-[50]">
      <div class="bg-white p-6 rounded-lg w-3/4">
        <h2 class="text-xl font-semibold mb-4">Detalles de la Orden</h2>
        <hr class="border-gray-300 mb-4">
        <div v-if="orderDetails && orderDetails.length > 0" class="max-h-96 overflow-y-auto">
          <div v-for="(detail, index) in orderDetails" :key="index" class="mb-4 border-b pb-2 mt-3">
            <div class="flex justify-between relative">
              <p class="flex-1"><strong>Producto:</strong> {{ detail.nombre }}</p>
              <p class="absolute left-[35%] transform -translate-x-1/2"><strong>Cantidad:</strong> {{ detail.cantidad }}</p>
              <p class="absolute left-[52%] text-left"><strong>Precio Unitario:</strong> ${{ detail.precioUnitario }}</p>
              <button @click="openModalReview(detail.nombre)" class="absolute right-[10%] top-[-8px] bg-green-500 text-white px-2 py-1 rounded">
                Valorar
              </button>
            </div>
          </div>
        </div>
        <div v-else>
          <p>No se encontraron detalles para esta orden.</p>
        </div>
        <button @click="closeModal" class="bg-red-500 text-white px-4 py-2 rounded mt-4">
          Cerrar
        </button>
      </div>
    </div>

     <!-- Modal con opcion para Valoracion -->
     <div v-if="isModalOpenReview" class="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-[60]">
      <div class="bg-white p-6 rounded-lg w-3/4">
        <div class= "flex flex-row justify-between">
        <h2 class="text-xl font-semibold mb-2 pt-3 ml-2">Valorar Producto: {{ productReview }}</h2>
          <div class="flex items-center mb-2 mr-6 pb-3">
            <p class="mr-2"><strong>Calificación:</strong></p>
            <v-rating v-model="rating" :max="5" />
          </div>
        </div>
        <hr class="border-gray-300 mb-4">
        <div class="mb-4 border-b pb-2 mt-3">
          <div class="flex flex-column justify-between relative">
            <div class="flex flex-column">
              <p class="ml-2 p-2">Comentario:</p>
              <textarea v-model="comment" class="border border-gray-300 bg-gray-200 rounded p-1" rows="3" cols="30"></textarea>
            </div>
          </div>
        </div>
        <button @click="sendReview" class="bg-green-500 text-white px-4 py-2 rounded mt-4">
          Enviar
        </button>
        <button @click="closeModalReview" class="bg-red-500 text-white px-4 py-2 rounded mt-4 ml-2">
          Cancelar
        </button>
      </div>
    </div>
  
      <!-- Vista para Productos -->
      <div v-if="activeTab === 'productos'">
        <h2 class="text-2xl font-semibold mb-4">Productos</h2>
        <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
          <div
            v-for="producto in paginatedProductos"
            :key="producto.idProducto"
            class="border border-gray-300 rounded-lg shadow-md p-4"
          >
            <h2 class="text-xl font-semibold">{{ producto.nombre }}</h2>
            <p>{{ producto.descripcion }}</p>
            <p class="text-lg font-bold">Precio: ${{ producto.precio }}</p>
            <p class="text-sm">Stock: {{ producto.stock }}</p>
          </div>
        </div>
      </div>
  
      <!-- Vista para Interacciones -->
      <div v-if="activeTab === 'interacciones'">
        <h2 class="text-2xl font-semibold mb-4">Interacciones</h2>
        <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
          <div
            v-for="interaccion in paginatedInteracciones"
            :key="interaccion.fecha"
            class="border border-gray-300 rounded-lg shadow-md p-4"
          >
            <h2 class="text-xl font-semibold mb-2">Tipo: {{ interaccion.tipo }}</h2>
            <hr class="my-2 border-gray-400">
            <p>{{ interaccion.descripcion }}</p>
            <p class="text-sm text-gray-600">Fecha: {{ formatDate(interaccion.fecha) }}</p>
          </div>
        </div>
      </div>
  
      <!-- Paginación -->
      <v-pagination
        v-model="currentPage"
        :length="totalPages"
        color="primary"
        class="mt-6"
      />
    </div>
  </template>
  
  <script setup>
    import { ref, computed } from 'vue'
    import axios from 'axios'
    
    const API_URL = 'http://localhost:8090/api/History'
    const API_URL_2 = 'http://localhost:8090/api/detalleorden'; // URL del backend de DetalleOrden
    const API_URL_3 = 'http://localhost:8090/api/producto'; // URL del backend de Producto
    const API_URL_4 = 'http://localhost:8090/api/review'; // URL del backend de Valoración
    
    const tabs = ref([
        { name: 'ordenes', label: 'Órdenes' },
        { name: 'productos', label: 'Productos' },
        { name: 'interacciones', label: 'Interacciones' },
    ])
    
    const activeTab = ref('ordenes') // Pestaña activa
    const currentPage = ref(1) // Página actual
    const itemsPerPage = 8 // Elementos por página
    
    const rating = ref(0); // Calificación del producto
    const comment = ref(''); // Comentario del producto
    const productReview = ref(''); // Producto a valorar
    
    const ordenes = ref([])
    const productos = ref([])
    const interacciones = ref([])
    const totalPages = ref(1)
    
    // Estado del modal
    const isModalOpen = ref(false);
    const isModalOpenReview = ref(false);
    const orderDetails = ref(null);

    // Abrir el modal
    const openModal = async (id_orden) => {
        try {
            const response = await axios.get(`${API_URL_2}/getByOrderId/${id_orden}`);

            const orderDetailsWithNames = await Promise.all(
            response.data.map(async (orderDetail) => {
                try {
                const productResponse = await axios.get(`${API_URL_3}/getName/${orderDetail.idProducto}`);
                return {
                    ...orderDetail,
                    nombre: productResponse.data,
                };
                } catch (error) {
                console.error('Error fetching product name:', error);
                return {
                    ...orderDetail,
                    nombre: 'Producto no encontrado',
                };
                }
            })
            );
            orderDetails.value = orderDetailsWithNames; // Guarda DetalleOrden
            isModalOpen.value = true; // Abre el popup
        } catch (error) {
            console.error('Error fetching order details:', error);
        }
    };

    const openModalReview = (nombre) => {
        productReview.value = nombre;
        isModalOpenReview.value = true;
    };

    const closeModalReview = () => {
        isModalOpenReview.value = false;
        productReview.value = '';
        rating.value = 0;
        comment.value = '';
    };

    const sendReview = async () => {
        const idCliente = localStorage.getItem('userId');
        const idProducto = orderDetails.value.find((detail) => detail.nombre === productReview.value).idProducto;
        const fecha = new Date();

        try {
            await axios.post(`${API_URL_4}/create`,   {
            productId: idProducto,
            comentario: comment.value,
            puntuacion: rating.value,
            id_usuario: idCliente,
            fecha,
            });
            alert('Valoración enviada con éxito');
            closeModalReview();
        } catch (error) {
            console.error('Error sending review:', error);
            alert('Error al enviar la valoración');
        }
    };

    const closeModal = () => {
        isModalOpen.value = false;
        orderDetails.value = null; 
    };



    const setActiveTab = (tab) => {
        activeTab.value = tab
        currentPage.value = 1
    }
    
    const fetchHistorial = async () => {
        const idUsuario = localStorage.getItem('userId')
        try {
            const response = await axios.get(`${API_URL}/history/${idUsuario}`)
            ordenes.value = response.data.ordenes
            productos.value = response.data.productos
            interacciones.value = response.data.interacciones
            totalPages.value = Math.ceil(
                getCurrentTabData().length / itemsPerPage
            )
        } catch (error) {
            console.error('Error fetching historial:', error)
        }
    }
    
    // Helpers
    const getCurrentTabData = () => {
        if (activeTab.value === 'ordenes') return ordenes.value
        if (activeTab.value === 'productos') return productos.value
        if (activeTab.value === 'interacciones') return interacciones.value
        return []
    }
    
    const paginatedOrdenes = computed(() =>
        ordenes.value.slice((currentPage.value - 1) * itemsPerPage, currentPage.value * itemsPerPage)
    )
    
    const paginatedProductos = computed(() =>
        productos.value.slice((currentPage.value - 1) * itemsPerPage, currentPage.value * itemsPerPage)
    )
    
    const paginatedInteracciones = computed(() =>
        interacciones.value.slice((currentPage.value - 1) * itemsPerPage, currentPage.value * itemsPerPage)
    )
    
    const formatDate = (date) => new Date(date).toLocaleDateString()
    
    const getStateColor = (estado) => {
        return estado === 'Activo'
        ? 'text-green-500'
        : estado === 'Pendiente'
        ? 'text-yellow-500'
        : 'text-red-500'
    }
    
    fetchHistorial()
  </script>
  
  