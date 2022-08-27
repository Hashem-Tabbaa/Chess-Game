package move;

public class MovementHandlerFactory {
    public MovementHandler createMovementHandler() {
        MovementHandler regularMovementHandler = new RegularMovementHandler();
        MovementHandler castlingHandler = new CastlingHandler();
        MovementHandler promotionHandler = new PromotionHandler();

        promotionHandler.setNextHandler(castlingHandler);
        castlingHandler.setNextHandler(regularMovementHandler);

        return promotionHandler;
    }
}
