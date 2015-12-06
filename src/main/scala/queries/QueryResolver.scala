package queries

trait QueryResolver {
  def Resolve(query: Query) : QueryResult;
}