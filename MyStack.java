public class MyStack<AnyType> implements Iterable<AnyType>
{
    public MyStack( )
    {
        clear( );
    }
    
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    public AnyType top( )
    {
        return theItems[ size() - 1 ];    
    }
        
    @SuppressWarnings("unchecked")
    public void ensureCapacity( int newCapacity )
    {
        if( newCapacity < theSize )
            return;

        AnyType [ ] old = theItems;
        theItems = (AnyType []) new Object[ newCapacity ];
        for( int i = 0; i < size( ); i++ )
            theItems[ i ] = old[ i ];
    }

    public void push( AnyType x )
    {
        if( theItems.length == size( ) )
            ensureCapacity( size( ) * 2 + 1 );
            
        theItems[ size() ] = x;
        theSize++;  
    }

    public void remove( )
    {
        theSize--;
    }
    
    public void pop()
    {
        theSize--;
    }

    public void clear( )
    {
        theSize = 0;
        ensureCapacity( DEFAULT_CAPACITY );
    }
    
    public java.util.Iterator<AnyType> iterator( )
    {
        return new ArrayListIterator( );
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType>
    {
        private int current = 0;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current < size( );
        }
        
        
        public AnyType next( )
        {
            if( !hasNext( ) ) 
                throw new java.util.NoSuchElementException( ); 
                  
            okToRemove = true;    
            return theItems[ current++ ];
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyStack.this.remove( );
            okToRemove = false;
        }
    }
    
    private static final int DEFAULT_CAPACITY = 10;
    
    private AnyType [ ] theItems;
    private int theSize;

    public static void main( String [ ] args )
    {
        String [] x = "[({}{})]".split("");
        MyStack<String> s = new MyStack<String>( );
        // for( int i = 0; i < x.length; i++) System.out.println(x[i]);
        boolean isValid = true;
        for( int i = 0; i < x.length; i++){
            if(s.isEmpty()) {
                s.push(x[i]);
            }
            else {
                if(x[i].equals("]") && s.top().equals("[")) {
                    s.pop();
                }
                else if(x[i].equals(")") && s.top().equals("(")) {
                    s.pop();
                }
                else if(x[i].equals("}") && s.top().equals("{")) {
                    s.pop();
                }
                else if(x[i].equals("[") || x[i].equals("(") || x[i].equals("{")){
                    s.push(x[i]);
                }
                else{
                    isValid = false;
                }
            }
        }
        if(!s.isEmpty()) {
            isValid = false;
        }
        System.out.println(isValid);
    }
}